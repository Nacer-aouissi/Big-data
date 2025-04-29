# 📊 TP 2: Reading Big CSV Files Using Chunk, Dask and Compression

![Python](https://img.shields.io/badge/Python-3.8+-blue.svg)
![Pandas](https://img.shields.io/badge/Pandas-1.3+-green.svg)
![Dask](https://img.shields.io/badge/Dask-2021.11+-orange.svg)

## 📝 Overview

This project compares three different methods for processing large CSV files:

1. 🔄 Chunk-based processing
2. ⚡ Dask DataFrame
3. 📦 Compression techniques

The goal is to determine which method is the fastest and most suitable for processing large datasets.

## 📁 Dataset

| Property        | Value                                                                                                                                          |
| --------------- | ---------------------------------------------------------------------------------------------------------------------------------------------- |
| **Source**      | [IBM Transactions for Anti-Money Laundering (AML)](https://www.kaggle.com/datasets/ealtman2019/ibm-transactions-for-anti-money-laundering-aml) |
| **Description** | Dataset containing transaction records for AML analysis                                                                                        |
| **Format**      | CSV                                                                                                                                            |
| **Size**        | Large-scale dataset                                                                                                                            |

## 🔄 Methods Comparison

### 1. Chunk-based Processing

> 💡 **Key Features:**
>
> - Processes data in smaller chunks to manage memory efficiently
> - Suitable for very large files that don't fit in memory
> - Allows for parallel processing

### 2. Dask DataFrame

> 💡 **Key Features:**
>
> - Distributed computing framework
> - Provides pandas-like interface
> - Handles out-of-memory computations

### 3. Compression

> 💡 **Key Features:**
>
> - Reduces file size and I/O operations
> - Various compression formats available (gzip, bz2, etc.)
> - Can be combined with other methods

## 📊 Performance Metrics

| Metric             | Description                           |
| ------------------ | ------------------------------------- |
| ⏱️ Processing time | Time taken to process the dataset     |
| 💾 Memory usage    | RAM utilization during processing     |
| 📥 I/O operations  | File read/write operations            |
| 📈 Scalability     | Performance with increasing data size |

## 📈 Results

> 🔄 _Results and comparisons will be added after running the analysis_

---

<div align="center">
  <sub>Built with ❤️ by Your Name</sub>
</div>
