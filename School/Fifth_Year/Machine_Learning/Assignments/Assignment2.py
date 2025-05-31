import torch
import csv
import matplotlib.pyplot as plt

with open('assign2.csv') as csvfile:
    reader = csv.reader(csvfile, delimiter=',')
    next(csvfile) # skip the first line
    xs, ys = [], []
    for row in reader:
        xs.append(float(row[0]))
        ys.append(float(row[1]))
        
xs, ys = torch.tensor(xs), torch.tensor(ys)

#get length of matrix
n = torch.numel(xs)

#create matrices for X and y
X = torch.ones(2,n)

#recreating ys using 1s
y = torch.ones(1,n)
y[0] = ys

#fill second column with xs values
X[1] = xs

#X transposed
XT = X.transpose(0,1)

#(XT*X) within w
XTX = X.transpose(0,1).mm(X).inverse()

#everything except the y within w's function
XTXXT = XTX.mm(XT)

#w function
w = y.mm(XTXXT)

#Y funtion
Y = w.mm(X)

#print values
#print("X",'\n',X)
#print("XT",'\n',XT)
#print("XTX",'\n',XTX)
#print("XTXXT",'\n',XTXXT)
#print("y",'\n',y)
print("w",'\n',w)
print("Y",'\n',Y)


#plotting
plt.scatter(xs.numpy(), ys.numpy())
plt.plot(Y)
plt.show()

#check for finished state
print('Done')
