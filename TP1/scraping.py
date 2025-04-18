from bs4 import BeautifulSoup
import os
import pandas as pd

def scrape_saved_pages(folder_path):
    all_episodes = []

    for file_name in sorted(os.listdir(folder_path)):  
        if file_name.endswith(".html"):  
            file_path = os.path.join(folder_path, file_name)
            print(f"Scraping file: {file_name}...")

            with open(file_path, "r", encoding="utf-8") as file:
                content = file.read()

            soup = BeautifulSoup(content, 'html.parser')
            episodes_containers = soup.find_all('div', class_="anime-card-container")

            for episode in episodes_containers:
                try:
                    anime_name = episode.find('div', class_="anime-card-title").text.strip()

                    link = episode.find('a', class_="overlay")['href']

                    all_episodes.append({
                        "Anime Name": anime_name,
                        "Link": link
                    })

                except Exception as e:
                    print(f"Error extracting episode data: {e}")

    print(f"Extracted {len(all_episodes)} episodes from {len(os.listdir(folder_path))} files.")
    return all_episodes


folder_path = "m_anime_pages"  
episodes = scrape_saved_pages(folder_path)

df = pd.DataFrame(episodes)
df.to_csv("m_anime_episodes.csv", index=False)
print("Data saved to anime_episodes.csv")
