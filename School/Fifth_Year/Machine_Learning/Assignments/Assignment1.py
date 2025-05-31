import torch

xs = torch.rand(30)
print(xs)

ys = torch.mean(xs)
print("Mean: ",ys)

zs = torch.std(xs)
print("Standard: ",zs)
