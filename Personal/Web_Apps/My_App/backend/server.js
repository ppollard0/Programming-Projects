const express = require('express');

const app = express();
const port = 3500;

app.use(express.json());

app.listen(port, () => {
  console.log(`Server is running on http://localhost:${port}`);
});

app.get('/', (req, res) => {
  res.status(200).send("Yes, I'm running.");
});

app.get('/api', (req, res) => {
  res.status(200).json({
    messages: {
      M1: "Backend is Connected.",
      M2: "This is also a message."
    }
  });
});