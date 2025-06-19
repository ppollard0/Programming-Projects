const express = require('express');

const app = express();
const port = 5000;

app.use(express.json());

app.listen(port, () => {
  console.log(`Server is running on http://localhost:${port}`);
});

app.get('/', (req, res) => {
  res.status(200).send("Yes, I'm running.");
});

app.get('/api', (req, res) => {
  messages = [
    "Backend is Connected.",
    "This and the previous message are backend messages."
  ]
  res.status(200).json(messages);
});