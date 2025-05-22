import os
import re

def add_swagger_import_to_java_files(root_dir):
    """
    在指定目录及其子目录中查找所有DTO/Vo文件，并添加Swagger import语句
    :param root_dir: 要搜索的根目录
    """
    # 匹配以DTO.java或Vo.java结尾的文件
    pattern = re.compile(r'.*(DTO|Vo)\.java$', re.IGNORECASE)
    
    for root, _, files in os.walk(root_dir):
        for file in files:
            if pattern.match(file):
                file_path = os.path.join(root, file)
                print(f'path: {file_path}')
                process_java_file(file_path)

def process_java_file(file_path):
    """
    处理单个Java文件，添加import语句
    :param file_path: Java文件路径
    """
    with open(file_path, 'r+', encoding='utf-8') as f:
        lines = f.readlines()
        
        # 查找package行的位置
        package_line_index = -1
        for i, line in enumerate(lines):
            if line.strip().startswith('package '):
                package_line_index = i
                break
        
        if package_line_index == -1:
            print(f"警告: {file_path} 中没有找到package声明，跳过处理")
            return
        
        # 检查是否已存在该import
        import_statement = 'import io.swagger.annotations.ApiModelProperty;\n'
        if any(import_statement.strip() in line.strip() for line in lines):
            print(f"跳过: {file_path} 中已存在ApiModelProperty导入")
            return
        
        # 在package行后插入import
        lines.insert(package_line_index + 1, import_statement)
        
        # 写回文件
        f.seek(0)
        f.writelines(lines)
        f.truncate()
        print(f"成功: 已处理 {file_path}")

if __name__ == '__main__':
    # 设置要搜索的目录（当前目录）
    target_directory = '.'
    
    print("开始处理Java文件...")
    add_swagger_import_to_java_files(target_directory)
    print("处理完成！")
