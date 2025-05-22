import os
import re

def add_swagger_import_to_java_files(root_dir):
    """
    在指定目录及其子目录中查找所有DTO/Vo文件，并添加Swagger import语句
    :param root_dir: 要搜索的根目录
    """
    # 匹配以DTO.java或Vo.java结尾的文件
    pattern = re.compile(r'.*\.java$', re.IGNORECASE)
    
    for root, _, files in os.walk(root_dir):
        for file in files:
            if pattern.match(file):
                file_path = os.path.join(root, file)
                print(f'path: {file_path}')
                process_java_file_2(file_path)

def process_java_file(file_path):
    """
    处理单个Java文件，添加import语句
    :param file_path: Java文件路径
    """
    with open(file_path, 'r+', encoding='utf-8') as f:
        lines = f.readlines()
        # 检查是否以这4行注释结尾
        if len(lines) >= 4 and lines[-4].startswith('/* Location:') and \
            lines[-3].strip().startswith('* Java compiler version:') and \
            lines[-2].strip().startswith('* JD-Core Version:') and \
            lines[-1].strip() == '*/':
                # 删除最后4行
                lines = lines[:-4]
                with open(file_path, 'w', encoding='utf-8') as f:
                    f.writelines(lines)

def clean_line_comment(line):
    # 匹配以 /*    */ 或 /*数字*/ 开头，长度为9的注释
    pattern = r'^/\*.+\*/.*'
    if re.match(pattern, line) and len(line) >= 9:
        print('modify')
        return line[9:]  # 删除前9个字符
    return line

def process_java_file_2(file_path):
    """
    清理每行开头/*    */的注释
    """
    with open(file_path, 'r+', encoding='utf-8') as f:
        lines = f.readlines()
        new_lines = [clean_line_comment(line) for line in lines]
        with open(file_path, 'w', encoding='utf-8') as f:
            f.writelines(lines)

if __name__ == '__main__':
    # 设置要搜索的目录（当前目录）
    target_directory = '../'
    
    print("开始处理Java文件...")
    add_swagger_import_to_java_files(target_directory)
    print("处理完成！")
