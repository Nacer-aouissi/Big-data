import requests
from bs4 import BeautifulSoup
import os

base_url = "https://anime4up.rest/episode/page/"
save_directory = "anime_pages"

# التأكد من وجود مجلد الحفظ
if not os.path.exists(save_directory):
    os.makedirs(save_directory)

# ملف HTML النهائي الذي سيحتوي على كل الصفحات
merged_file_path = os.path.join(save_directory, "all_episodes_merged.html")

def download_and_merge_pages():
    merged_content = "<html><head><meta charset='utf-8'><title>All Episodes</title></head><body>\n"
    merged_content += "<h1>All Anime Episodes</h1>\n"

    for page_number in range(1, 494):  # تحميل الصفحات من 1 إلى 493
        page_url = f"{base_url}{page_number}/"
        try:
            response = requests.get(page_url)
            response.raise_for_status()

            # تحليل الصفحة باستخدام BeautifulSoup
            soup = BeautifulSoup(response.text, "html.parser")

            # استخراج فقط القسم الذي يحتوي على قائمة الحلقات (تعديل التحديد حسب هيكلة الموقع)
            main_content = soup.find("div", class_="container")  # قد تحتاج لتعديل هذا بناءً على كود الموقع

            if main_content:
                merged_content += str(main_content) + "\n"
            print(f"Merged page {page_number}")

        except requests.exceptions.RequestException as e:
            print(f"Error downloading {page_url}: {e}")

    merged_content += "</body></html>"

    # حفظ الملف النهائي
    with open(merged_file_path, "w", encoding="utf-8") as file:
        file.write(merged_content)

    print(f"All pages merged into {merged_file_path}")

download_and_merge_pages()
