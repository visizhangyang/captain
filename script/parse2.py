import os
import re

def add_swagger_import_to_java_files(root_dir):
    """
    在指定目录及其子目录中查找所有DTO/Vo文件，添加Swagger import语句并删除hashCode和equals方法
    :param root_dir: 要搜索的根目录
    """
    pattern = re.compile(r'.*(DTO|Vo)\.java$', re.IGNORECASE)
    
    for root, _, files in os.walk(root_dir):
        for file in files:
            if pattern.match(file):
                file_path = os.path.join(root, file)
                print(f'\n处理文件: {file_path}')
                process_java_file(file_path)

def process_java_file(file_path):
    """
    处理单个Java文件：添加Swagger import语句并删除hashCode和equals方法
    :param file_path: Java文件路径
    """
    with open(file_path, 'r+', encoding='utf-8') as f:
        lines = f.readlines()

    # 删除 hashCode 方法
    #lines = remove_method(lines, file_path, method_name="hashCode", return_type="int")

    # 删除 equals 方法
    #lines = remove_method(lines, file_path, method_name="equals", return_type="boolean", param_type="Object")

    # 删除 toString 方法
    lines = remove_method(lines, file_path, method_name="toString", return_type="String")

    # 查找 package 行
    #package_line_index = -1
    #for i, line in enumerate(lines):
    #    if line.strip().startswith('package '):
    #        package_line_index = i
    #        break

    #if package_line_index == -1:
    #    print(f"⚠️  警告: {file_path} 中没有找到 package 声明，跳过 import 添加")
    #    return

    ## 检查是否已存在 Swagger import
    #import_statement = 'import io.swagger.annotations.ApiModelProperty;\n'
    #if any(import_statement.strip() in line.strip() for line in lines):
    #    print("✅ 已存在 Swagger import，跳过导入添加")
    #else:
    #    lines.insert(package_line_index + 1, import_statement)
    #    print("✅ 添加了 Swagger import")

    # 写回文件
    with open(file_path, 'w', encoding='utf-8') as f:
        f.writelines(lines)
        print("✅ 写入修改完成")

def remove_method(lines, file_path, method_name, return_type, param_type=None):
    """
    删除 Java 类中的指定方法
    :param lines: Java 文件内容的行列表
    :param file_path: 文件路径（用于日志）
    :param method_name: 方法名（如 equals）
    :param return_type: 返回类型（如 boolean）
    :param param_type: 参数类型（可选，如 Object）
    :return: 处理后的行列表
    """
    new_lines = []
    inside_target = False
    brace_count = 0

    if param_type:
        method_pattern = re.compile(rf'\s*public\s+{re.escape(return_type)}\s+{re.escape(method_name)}\s*\(\s*{param_type}\s+\w+\s*\)\s*{{?')
    else:
        method_pattern = re.compile(rf'\s*public\s+{re.escape(return_type)}\s+{re.escape(method_name)}\s*\(\s*\)\s*{{?')
    
    for line in lines:
        if not inside_target and method_pattern.match(line):
            inside_target = True
            brace_count = line.count('{') - line.count('}')
            print(f"🗑️  检测到 {method_name} 方法，开始删除...")
            continue

        if inside_target:
            brace_count += line.count('{') - line.count('}')
            if brace_count <= 0:
                inside_target = False
                print(f"🗑️  {method_name} 方法删除完成")
            continue  # skip the line

        new_lines.append(line)

    if inside_target:
        print(f"⚠️  {file_path} 中 {method_name} 方法结构异常，可能未正确删除")
        return lines  # fallback

    return new_lines

if __name__ == '__main__':
    target_directory = '.'
    #target_directory = 'src/main/java/com/porn/client/account/dto'
    print("🚀 开始处理 Java DTO/Vo 文件...")
    add_swagger_import_to_java_files(target_directory)
    print("\n✅ 所有文件处理完成！")

