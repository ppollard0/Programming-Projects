#!/usr/bin/env python3
# ols.py                                                     SSimmons March 2018
"""
Uses a neural net to find the ordinary least-squares regression model. Trains
with batch gradient descent, and computes r^2 to gauge predictive quality.
"""

import torch
import pandas as pd
import torch.nn as nn

# Read the named columns from the csv file into a dataframe.
names = ['SalePrice','1st_Flr_SF','2nd_Flr_SF','Lot_Area','Overall_Qual',
    'Overall_Cond','Year_Built','Year_Remod/Add','BsmtFin_SF_1','Total_Bsmt_SF',
    'Gr_Liv_Area','TotRms_AbvGrd','Bsmt_Unf_SF','Full_Bath']
df = pd.read_csv('AmesHousing.csv', names = names)
data = df.values # read data into a numpy array (as a list of lists)
data = data[1:] # remove the first list which consists of the labels
data = data.astype(float) # coerce the entries in the numpy array to floats
data = torch.FloatTensor(data) # convert data to a Torch tensor

data.sub_(data.mean(0)) # mean-center
data.div_(data.std(0))  # normalize

xss = data[:,1:]
yss = data[:,:1]

# define a model class
class NonLinearModel(nn.Module):

  def __init__(self):
    super(NonLinearModel, self).__init__()
    self.layer1 = nn.Linear(13, 10)
    self.layer2 = nn.Linear(10, 1)

  def forward(self, xss):
    xss = self.layer1(xss)
    xss = torch.relu(xss)
    return self.layer2(xss)

# create and print an instance of the model class
model = NonLinearModel()
print(model)

criterion = nn.MSELoss()

num_examples = len(data)
batch_size = 100
learning_rate = .000355
epochs = 1000
beta = 0.899

# train the model
for epoch in range(epochs):
  for _ in range(num_examples//batch_size):
    # randomly pick batchsize examples from data
    indices = torch.randperm(num_examples)[:batch_size]

    yss_mb = yss[indices]  # the targets for the mb (minibatch)
    yhatss_mb = model(xss[indices])  # model outputs for the mb

    loss = criterion(yhatss_mb, yss_mb)
    model.zero_grad()
    loss.backward() # back-propagate

    # update weights
    for f in model.parameters():
      f.data.sub_(beta * f.grad.data * learning_rate)

  with torch.no_grad():
    total_loss = criterion(model(xss), yss).item()
  print('epoch: {0}, loss: {1:11.8f}'.format(epoch+1, total_loss))

# Weights in model counted
print()
for name, param in model.named_parameters():
  print(name, param.size())
print()

print("total number of examples:", num_examples, end='; ')
print("batch size:", batch_size)
print("learning rate:", learning_rate)
print("momentum:", beta)

# Compute 1-SSE/SST which is the proportion of the variance in the data
# explained by the regression hyperplane.
SS_E = 0.0;  SS_T = 0.0
mean = yss.mean()
for xs, ys in zip(xss, yss):
  SS_E = SS_E + (ys - model(xs))**2
  SS_T = SS_T + (ys - mean)**2
print(f"1-SSE/SST = {1.0-(SS_E/SS_T).item():1.4f}")
