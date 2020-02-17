import pandas
import csv, sqlite3
conn= sqlite3.connect("src/Film.db")
df = pandas.read_csv('../netflix_titles.csv')
df.to_sql('titles', conn, if_exists='replace', index=False)
df2 = pandas.read_csv('../netflix_directors.csv')
df2.to_sql('directors', conn, if_exists='replace', index=False)
