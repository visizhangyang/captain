import os

# 要添加的 import 语句
required_imports = [
    'import com.baomidou.mybatisplus.annotation.TableField;',
    'import com.baomidou.mybatisplus.annotation.TableName;',
    'import com.porn.service.common.entity.BaseDO;',
    'import java.math.BigDecimal;',
    'import java.time.LocalDateTime;'
]

def process_java_file(file_path):
    with open(file_path, 'r+', encoding='utf-8') as f:
        lines = f.readlines()

    # 查找 package 行的位置
    package_index = next((i for i, line in enumerate(lines) if line.strip().startswith('package ')), -1)

    if package_index == -1:
        print(f"⚠️ 跳过（未找到 package）：{file_path}")
        return

    # 检查已有 import
    existing_imports = set(line.strip() for line in lines if line.strip().startswith('import '))
    missing = [imp for imp in required_imports if imp not in existing_imports]

    if not missing:
        print(f"✅ 已完整导入：{file_path}")
        return

    # 插入缺失 import
    for imp in reversed(missing):  # 倒序插入，保持顺序
        lines.insert(package_index + 1, imp + '\n')

    # 写回文件
    with open(file_path, 'w', encoding='utf-8') as f:
        f.writelines(lines)

    print(f"🛠️ 已插入缺失 import：{file_path}")

def scan_directory(root_dir):
    for root, _, files in os.walk(root_dir):
        for file in files:
            if file.endswith('DO.java'):
                file_path = os.path.join(root, file)
                process_java_file(file_path)

if __name__ == '__main__':
    target_directory = '.'  # 可改为你的 Java 源码目录
    #target_directory = 'src/main/java/com/porn/service/autowork/dao/entity'  # 可改为你的 Java 源码目录
    print("🚀 正在处理 DO.java 文件...")
    scan_directory(target_directory)
    print("\n🎉 所有文件处理完成！")

