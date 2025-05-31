"""Code to read in the zero and eights: """
import torch
import torch.nn as nn
import du.lib as dulib
from skimage import io



# read in all of the digits
digits = io.imread('digits.png')
xss = torch.Tensor(5000,400)
idx = 0
for i in range(0, 1000, 20):
  for j in range(0, 2000, 20):
    xss[idx] = torch.Tensor((digits[i:i+20,j:j+20]).flatten())
    idx = idx + 1

# extract just the zeros and eights from xss
tempxss = torch.Tensor(1000,400)
tempxss[:500] = xss[:500]
tempxss[500:] = xss[4000:4500]

# overwrite the original xss with just zeros and eights
xss = tempxss

# generate yss to hold the correct classification for each example
yss = torch.LongTensor(len(xss),1)
for i in range(len(yss)):
  yss[i] = i//500

xss_means = xss.mean(0)  # xss_means.size() returns torch.size([2])
yss_means = yss.mean()   # yss_means.size() returns torch.size([])
xss_stds  = xss.std(0)   # similarly here
yss_stds  = yss.std()    # and here

# Center and Normalize inputs
xss, xss_means = dulib.center(xss)
xss, xss_stds = dulib.normalize(xss)

# Linear Model
class LinearModel(nn.Module):

  def __init__(self):
    super(LinearModel, self).__init__()
    self.layer1 = nn.Linear(400, 1)

  def forward(self, x):
    return self.layer1(x)
  

# create and print an instance of the model class
model = LinearModel()

criterion = nn.MSELoss()

# Set up check for accuracy
def pct_correct(yhatss, yss):
 zero = torch.min(yss).item()
 eight = torch.max(yss).item()
 th = 1e-3 # threshold
 cutoff = (zero+eight)/2
 count = 0
 for yhats, ys in zip(yhatss, yss):
   yhat = yhats.item()
   y = ys.item()
   if (yhat>cutoff and abs(y-eight)<th) or (yhat<cutoff and abs(y-zero)<th):
     count += 1
 return 100*count/len(yss)

#Train Model
model = dulib.train(
 model = model,
 crit = criterion,
 train_data = (xss, yss),
 valid_metric = pct_correct,
 epochs = 100,
 learn_params = {'lr':0.05, 'mo':0.9},
 verb = 2,
 graph =1
 )

print("Percentage correct:", pct_correct(model(xss), yss))
