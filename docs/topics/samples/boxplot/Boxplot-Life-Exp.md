# Boxplot Life Expectations by Country

<web-summary>
Uncover global health trends with 'Boxplot Life Expectations by Country' in Kandy.
This example uses boxplot to compare life expectancy across different countries, highlighting variations and patterns in public health.
</web-summary>

<card-summary>
'Boxplot Life Expectations by Country' in Kandy visualizes life expectancy variations worldwide.
</card-summary>

<link-summary>
Explore life expectancy trends globally with 'Boxplot Life Expectations by Country' in Kandy, showcasing international health patterns.
</link-summary>


<!---IMPORT org.jetbrains.kotlinx.kandy.letsplot.samples.Boxplot-->

<!---FUN boxplot_life_exp-->

```kotlin
val countries = arrayOf("Australia", "Canada", "China", "Germany", "Japan", "United Kingdom", "United States")

val australiaLifeExpectancy = listOf(
    87.29, 83.20, 84.93, 88.72, 87.60, 79.07, 84.85, 81.55, 81.69, 83.23,
    82.43, 86.36, 84.28, 82.37, 83.33, 83.00, 86.48, 81.38, 82.94, 79.44, 74.34, 83.96, 84.59, 79.77, 88.81,
    77.64, 82.14, 81.44, 86.60, 86.41, 82.46, 83.13, 79.34, 76.06, 80.96, 82.47, 85.69, 85.61, 80.84, 81.09,
    78.85, 77.74, 76.88, 87.85, 80.47, 80.69, 78.24, 84.33, 77.16, 81.36
)
val canadaLifeExpectancy = listOf(
    77.42, 82.55, 78.96, 76.28, 80.89, 82.71, 81.27, 82.21, 78.46, 79.55, 78.31,
    79.56, 77.75, 74.09, 81.71, 79.39, 74.48, 82.85, 77.37, 81.21, 83.92, 81.52, 85.56, 76.06, 82.61, 78.26,
    77.52, 78.68, 79.75, 81.22, 76.34, 84.60, 82.86, 74.86, 86.95, 88.58, 85.72, 80.28, 76.72, 85.22, 79.39,
    85.89, 81.83, 84.91, 82.43, 83.83, 81.04, 80.07, 90.56, 79.77
)
val chinaLifeExpectancy = listOf(
    76.08, 88.39, 75.42, 80.75, 79.44, 80.96, 86.69, 77.30, 81.14, 76.65, 63.39,
    88.37, 78.89, 73.96, 87.45, 84.74, 82.90, 78.07, 89.43, 86.43, 78.80, 73.51, 85.01, 81.11, 79.09, 83.57,
    83.69, 82.95, 77.55, 82.13, 79.85, 79.99, 78.67, 78.90, 79.85, 88.72, 72.23, 76.12, 74.75, 79.22, 85.58,
    81.17, 74.86, 72.02, 85.43, 73.34, 81.60, 83.86, 79.86, 75.95
)
val germanyLifeExpectancy = listOf(
    75.98, 73.25, 80.70, 69.84, 83.00, 82.91, 84.36, 75.75, 75.16, 85.21, 87.71,
    80.55, 92.80, 80.20, 86.26, 74.87, 81.98, 85.70, 83.06, 78.86, 79.04, 73.85, 79.78, 79.95, 78.07, 81.57,
    73.08, 72.05, 92.45, 76.85, 84.82, 80.43, 80.90, 80.26, 82.40, 80.84, 88.51, 87.48, 78.37, 71.97, 81.41,
    88.58, 84.84, 89.01, 80.05, 82.32, 81.44, 89.74, 79.14, 86.96
)
val japanLifeExpectancy = listOf(
    82.72, 83.77, 83.78, 78.10, 84.92, 80.83, 80.85, 80.02, 84.13, 82.95, 83.10,
    84.34, 83.39, 79.09, 82.43, 86.71, 81.56, 80.90, 86.52, 80.25, 81.95, 82.91, 78.74, 82.05, 80.53, 84.14,
    82.10, 81.56, 81.85, 87.35, 80.59, 83.85, 80.55, 85.67, 85.42, 83.47, 85.73, 80.95, 81.38, 82.91, 85.21,
    87.31, 83.02, 78.46, 78.69, 82.79, 88.07, 81.24, 72.21, 89.26
)
val ukLifeExpectancy = listOf(
    76.66, 82.38, 75.41, 77.95, 82.04, 81.03, 79.85, 75.26, 81.20, 81.83, 78.43,
    83.02, 79.70, 80.96, 81.24, 80.68, 75.77, 81.59, 77.71, 75.22, 82.07, 82.25, 79.45, 80.73, 79.35, 80.89,
    85.13, 78.91, 79.96, 82.28, 84.67, 77.76, 79.54, 85.25, 80.17, 80.98, 78.61, 78.73, 80.07, 77.14, 80.86,
    81.71, 78.17, 79.85, 73.72, 75.23, 77.12, 80.75, 79.22, 83.58
)
val usaLifeExpectancy = listOf(
    71.47, 86.29, 77.41, 74.60, 73.04, 77.52, 80.27, 76.45, 80.95, 72.47, 74.96,
    79.30, 79.07, 77.33, 83.71, 77.28, 74.23, 75.89, 81.34, 82.65, 82.96, 82.33, 83.89, 76.20, 77.53, 75.34,
    75.93, 78.74, 79.43, 74.50, 73.26, 77.73, 74.55, 73.39, 79.32, 74.14, 77.21, 73.90, 75.62, 72.88, 78.65,
    80.89, 79.75, 73.08, 72.05, 71.45, 73.85, 80.82, 79.43, 80.90
)

val lifeExpectancyData = dataFrameOf(
    "Australia" to australiaLifeExpectancy,
    "Canada" to canadaLifeExpectancy,
    "China" to chinaLifeExpectancy,
    "Germany" to germanyLifeExpectancy,
    "Japan" to japanLifeExpectancy,
    "United Kingdom" to ukLifeExpectancy,
    "United States" to usaLifeExpectancy
).gather(*countries).into("country", "lifeExp")

lifeExpectancyData.boxplot("country", "lifeExp").configure {
    coordinatesTransformation = CoordinatesTransformation.cartesianFlipped()
}
```

<!---END-->

![Boxplot Life Expectations by Country](boxplot_life_exp.svg) { border-effect="rounded" }

<seealso style="cards">
       <category ref="example-ktnb">
           <a href="https://github.com/Kotlin/kandy/blob/main/examples/notebooks/lets-plot/samples/boxplot/boxplot_life_exp.ipynb" summary="View the notebook on our GitHub repository">GitHub Notebook</a>
           <a href="https://datalore.jetbrains.com/report/static/KQKedA4jDrKu63O53gEN0z/hZIqYF8HdmXyGc9mVzfAFu" summary="Experiment with this example on Datalore">Datalore Notebook</a>
       </category>
</seealso>
