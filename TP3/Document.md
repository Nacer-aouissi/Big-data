# 📊 HR Analytics – Employee Attrition & Performance Analysis

![Python](https://img.shields.io/badge/Python-3.8+-blue.svg)
![Pandas](https://img.shields.io/badge/Pandas-1.3+-green.svg)
![Scikit-learn](https://img.shields.io/badge/Scikit--learn-1.0+-orange.svg)
![Power BI](https://img.shields.io/badge/Power%20BI-Desktop-purple.svg)

## 📁 Data Sources and Collection

| Property        | Value                                                                                                          |
| --------------- | -------------------------------------------------------------------------------------------------------------- |
| **Dataset**     | IBM HR Analytics Employee Attrition & Performance                                                              |
| **Source**      | Excel BI Analytics                                                                                             |
| **Format**      | CSV                                                                                                            |
| **Size**        | ~500MB                                                                                                         |
| **Description** | Detailed employee records including demographics, job roles, satisfaction scores, income, and attrition status |

## 🔧 Data Cleaning and Transformation

### 🧹 Data Cleaning Steps

```python
# Remove duplicates
df = df.drop_duplicates()

# Handle missing values
df.dropna()  # Remove rows with nulls

# Convert categorical columns
from sklearn.preprocessing import LabelEncoder
for col in categorical_columns:
    df[col] = label_enc.fit_transform(df[col])

# Map target column
df['Attrition'] = df['Attrition'].map({'Yes': 1, 'No': 0})

# Export cleaned data
df.to_csv("HR_Analytics_Cleaned.csv", index=False)
```

## 📊 Analysis and Insights

### 🔍 Exploratory Data Analysis (EDA)

- 📈 Sales trends visualization
- 💰 Income distribution analysis
- 👥 Attrition pattern analysis
- 🔗 Feature correlation study

> 📊 **Analysis Dashboard**: [View Analysis](https://limewire.com/d/LWv94#8XEhgyQAsF)

### 🤖 Machine Learning Analysis

#### Random Forest Classifier

```python
# Model Evaluation
accuracy_score(y_test, y_pred)
classification_report(y_test, y_pred)
```

#### Key Findings 🔑

1. 🎯 Job Satisfaction Impact

   - Strong correlation with attrition rates
   - Critical factor in employee retention

2. 💵 Income Influence

   - Direct relationship with employee turnover
   - Higher income brackets show lower attrition

3. 🏢 Department Patterns
   - Higher travel requirements → Increased attrition
   - Lower satisfaction scores → Higher exit rates

## 📱 Power BI Dashboard

### 📂 Dashboard Components

#### 1. Overview Page

- 👥 Employee Count KPI
- 📉 Attrition Rate Metrics

#### 2. Visualizations

- 🥧 Pie Chart: Attrition Distribution
- 📊 Column Chart: Income Distribution
- 📈 Bar Chart: Department-wise Attrition
- 🔥 Heatmap: Job Satisfaction vs Attrition

#### 3. Interactive Features

- 🔍 Slicers:
  - Gender
  - Job Role
  - Education Level
- 🔎 Drill-through Capabilities
  - Detailed attrition metrics
  - Role-specific analysis

### 🚀 How to Use the Dashboard

1. 📂 Open `Analyses.pbix` in Power BI Desktop
2. 🔍 Use top slicers for group exploration
3. 👆 Right-click for detailed drill-through
4. 📊 Apply filters for comparative analysis

---

<div align="center">
  <sub>Built with ❤️ by Your Name</sub>
</div>
