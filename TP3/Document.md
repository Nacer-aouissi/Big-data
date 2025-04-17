# Project Documentation

## Project Title:

HR Analytics – Employee Attrition & Performance Analysis

## Data Sources and Collection Methods :

- Dataset Used: IBM HR Analytics Employee Attrition & Performance

- Source: Downloaded from Excel BI Analytics

- File Format: CSV

- Size: ~500MB

- Description: The dataset contains detailed records of employees, including demographics, job roles, satisfaction scores, income, and whether they left the company or not (Attrition)

## Data Cleaning and Transformation :

- Performed using Python (Pandas & Scikit-learn):

`df = df.drop_duplicates()`

- Handled Missing Values:

- Dropped rows with nulls using : `df.dropna()`

- Optionally filtered out columns with excessive missing data

- Converted Categorical Columns to Numerical:

Used LabelEncoder from sklearn to encode fields like Gender, JobRole, Department, etc.

```
from sklearn.preprocessing import LabelEncoder
for col in categorical_columns:
df[col] = label_enc.fit_transform(df[col])
```

- Mapped Target Column (Attrition) to binary:

`df['Attrition'] = df['Attrition'].map({'Yes': 1, 'No': 0})`

- Exported the cleaned dataset:

`df.to_csv("HR_Analytics_Cleaned.csv", index=False)`

## Analysis Performed and Insights Derived :

- EDA (Exploratory Data Analysis):

Visualized sales trends, income distribution, and attrition counts using Seaborn/Matplotlib Analyzed correlations between features

- Advanced Analysis:

Built a Random Forest classifier to predict employee attrition

Evaluated model using Accuracy, Precision, Recall, and Confusion Matrix

```
accuracy_score(y_test, y_pred)
classification_report(y_test, y_pred)
```

- Key Insights:

Job Satisfaction and Monthly Income had a noticeable relationship with attrition

Departments with higher travel or lower satisfaction scored higher in employee exits

## Power BI Dashboard Usage :

### File Used:

Analyses.pbix

### Data Source:

HR_Analytics_Cleaned.csv

### Dashboard Features:

- Overview Page:

Total number of employees

Attrition rate as KPI cards

- Visualizations:

- Pie Chart: Employee Attrition Rate

- Column Chart: Monthly Income Distribution (binned)

- Bar Chart: Attrition by Department

- Matrix Table: Job Satisfaction vs Attrition (heatmap style)

- Interactive Elements:

Slicers for filtering by Gender, Job Role, and Education

Drillthrough enabled on Job Role to show detailed attrition metrics

- How to Use:

Open the Analyses.pbix file in Power BI Desktop

Use slicers at the top to explore specific groups

Right-click any bar or data point → Drillthrough to deep-dive

Use filters to compare trends across departments, gender, and income levels
