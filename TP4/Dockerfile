FROM jupyter/base-notebook:latest

COPY Analyse.ipynb /home/jovyan/
COPY Analyses.pbix /home/jovyan/

RUN pip install pandas matplotlib seaborn scikit-learn dask

WORKDIR /home/jovyan/

EXPOSE 8888
