"""Code to read in the zero and eights: """
import torch
import torch.nn as nn
import du.lib as dulib
from skimage import io



# read in all of the digits
digits = io.imread('digits.png')
xss = torch.Tensor(5000,20,20)
idx = 0
for i in range(0, 1000, 20):
  for j in range(0, 2000, 20):
    xss[idx] = torch.Tensor((digits[i:i+20,j:j+20]))
    idx = idx + 1

# generate yss to hold the correct classification for each example
yss = torch.LongTensor(len(xss))
for i in range(len(yss)):
  yss[i] = i//500

# Center and Normalize inputs
xss, xss_means = dulib.center(xss)
xss, xss_stds = dulib.normalize(xss)

# Train/Test Data
indices = torch.randperm(len(xss))
xss, yss = xss[indices], yss[indices] # coherently randomize the data
xss_train, yss_train = xss[:4000], yss[:4000]
xss_test, yss_test = xss[4000:], yss[4000:]

# Set Model
class ConvolutionalModel(nn.Module):

  def __init__(self):
    super(ConvolutionalModel, self).__init__()
    self.meta_layer1 = nn.Sequential(
        nn.Conv2d(in_channels=1, out_channels=16, kernel_size=5, stride=1, padding = 2),
        nn.ReLU(),
        nn.MaxPool2d(kernel_size = 2, stride = 2, padding = 0)
    )
    self.fc_layer1 = nn.Linear(1600,10)

  def forward(self, xss):
    xss = torch.unsqueeze(xss, dim=1)
    xss = self.meta_layer1(xss)
    xss = torch.reshape(xss, (-1, 1600))
    xss = self.fc_layer1(xss)
    return torch.log_softmax(xss, dim=1)

# create an instance of the model class
model = ConvolutionalModel()

# set the criterion
criterion = nn.NLLLoss()

#Train Model
model = dulib.train(
  model = model,
  crit = criterion,
  train_data = (xss_train, yss_train),
  valid_data = (xss_test, yss_test),
  epochs = 150,
  learn_params = {'lr':0.05, 'mo':0.9},
  verb = 2,
  graph =1)

pct_training = dulib.class_accuracy(model, (xss_train, yss_train), show_cm=True)
pct_test = dulib.class_accuracy(model, (xss_test, yss_test), show_cm=True)
print(f"Percentage correct on training data: {100*pct_training:.2f}")
print(f"Percentage correct on test data: {100*pct_test:.2f}")
