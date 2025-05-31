#!/usr/bin/env python3
# ols.py                                                     SSimmons March 2018
"""
Uses a neural net to find the ordinary least-squares regression model. Trains
with batch gradient descent, and computes r^2 to gauge predictive quality.
"""

import torch
import pandas as pd
import torch.nn as nn
import du.lib as dulib

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

#Training data
xss_train, xss_test = xss[:1812], xss[1812:]
yss_train, yss_test = yss[:1812], yss[1812:]

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
model = dulib.train(
  model = NonLinearModel(),
  crit = nn.MSELoss(),
  train_data = (xss_train, yss_train),
  valid_data = (xss_test, yss_test),
  epochs = 100,
  learn_params = {'lr':0.05, 'mo':0.9},
  bs = 2264,
  verb = 2,
  graph = 1)

print("explained variation:", dulib.explained_var(model, (xss_train, yss_train)))
