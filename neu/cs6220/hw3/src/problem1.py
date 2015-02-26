from naive_bayes import NaiveBayes

training_set = [
        ['High School', 'Management', 'Less than 3', 'Low'],
        ['High School', 'Management', '3 to 10', 'Low'],
        ['College', 'Management', 'Less than 3', 'High'],
        ['College', 'Service', 'More than 10', 'Low'],
        ['High School', 'Service', '3 to 10', 'Low'],
        ['College', 'Service', '3 to 10', 'High'],
        ['College', 'Management', 'More than 10', 'High'],
        ['College', 'Service', 'Less than 3', 'Low'],
        ['High School', 'Management', 'More than 10', 'High'],
        ['High School', 'Service', 'More than 10', 'Low']
    ]

nb = NaiveBayes(training_set)


new_data = [
        ['High School', 'Service', 'Less than 3'],
        ['College', 'Retail', 'Less than 3'],
        ['Graduate', 'Service', '3 to 10']
        ]

for data in new_data:
    print nb.classify(data)
