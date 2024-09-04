const express=require('express')
const mongoose = require('mongoose');

const app=express()
app.set('view engine', 'ejs');

app.use(express.urlencoded({ extended: true })); // Parse URL-encoded bodies

const itemSchema = new mongoose.Schema({
  id: Number,
  Item: String,
  Quantity: Number,
  Price: Number,
  Description: String
});

const Items= mongoose.model('inventory1', itemSchema); 

// Connect to MongoDB
mongoose.connect('mongodb://localhost:27017/student')
  .then(() => {
    console.log('Connected to MongoDB');
  })
  .catch(err => {
    console.error('Error connecting to MongoDB:', err);
  });

 app.get("/", (req, res) => {
    res.send("Hello! Welcome To The Inventory . . . . .");
});

//To get the item details ,to add into database
app.get('/inventory/add', (req, res) => {
  res.render('add_item'); 
});

//To post the item details into database
app.post('/inventory/add', async (req, res) => {
  try {
    // Extract item details from the request body
    const { id, Item, Quantity, Price, Description } = req.body;
    
    const itemExist = await Items.findOne({ id });
    if (itemExist) {
      return res.status(400).send("User with this ID already exists");
    }

    // Create a new item instance
    const newItem = new Items({ id, Item, Quantity, Price, Description });
    
    // Save the new item to the database
    await newItem.save();
    
    // Redirect to the inventory page after adding the item
    res.redirect('/inventory');
  } catch (err) {
    console.error('Error adding item:', err);
    res.status(500).send('Internal Server Error');
  }
});


//To display all items
app.get("/inventory",async (req, res) => {
  try {
    const inventory = await Items.find(); // Fetch all items from the database
    res.render("inventory", { inventory });
  } catch (err) {
    console.error('Error fetching inventory:', err);
    res.status(500).send('Internal Server Error');
  }
});

//To display the item with specific id
app.get("/inventory/:id", async (req, res) => {
  try {
    const item = await Items.findOne({ id: req.params.id }); // Find item by id
    if (!item) {
      return res.send("Error! The item with given id doesn't exist");
    }
    res.render("item", { item });
  } catch (err) {
    console.error('Error fetching item:', err);
    res.status(500).send('Internal Server Error');
  }
});

//To delete specific item with delete HTTPMETHOD
// app.delete("/inventory/:id", async (req, res) => {
//   try {
//     const itemId = req.params.id;
//     // Delete item from database using its ID
//     const deletedItem = await Items.findOneAndDelete({ id: itemId });
//     if (!deletedItem) {
//       return res.status(404).send("Item not found");
//     }
//     res.send(`Item with ID ${itemId} has been deleted`);
//   } catch (err) {
//     console.error('Error deleting item:', err);
//     res.status(500).send('Internal Server Error');
//   }
// });


// to delete with specific item using get and post
app.get("/inventory/delete/:id", async (req, res) => {
  try {
    const itemId = req.params.id;
    const item = await Items.findOne({ id: itemId });
    if (!item) {
      return res.status(404).send("Item not found");
    }
    res.render("delete_item", { item });
  } catch (err) {
    console.error('Error fetching item:', err);
    res.status(500).send('Internal Server Error');
  }
});

app.post("/inventory/delete/:id", async (req, res) => {
  try {
    const itemId = req.params.id;
    const deletedItem = await Items.findOneAndDelete({ id: itemId });
    if (!deletedItem) {
      return res.status(404).send("Item not found");
    }
    res.redirect('/inventory')
    //res.send(`Item with ID ${itemId} has been deleted`);
  } catch (err) {
    console.error('Error deleting item:', err);
    res.status(500).send('Internal Server Error');
  }
});


// to render the update item form
app.get("/inventory/update/:id", async (req, res) => {
  try {
    const itemId = req.params.id;
    const item = await Items.findOne({ id: itemId });
    if (!item) {
      return res.status(404).send("Item not found");
    }
    // Render the update item form with itemId
res.render("update_item", { itemId: req.params.id, item });

  } catch (err) {
    console.error('Error fetching item:', err);
    res.status(500).send('Internal Server Error');
  }
});

// to update item form submission
app.post("/inventory/:id", async (req, res) => {
  try {
    const itemId = req.params.id;
    // Find the item in the database
    const item = await Items.findOne({ id: itemId });
    if (!item) {
      return res.status(404).send("Item not found");
    }
    // Update the item fields based on the form data
    item.Item = req.body.Item;
    item.Quantity = req.body.Quantity;
    item.Price = req.body.Price;
    item.Description = req.body.Description;
    // Save the updated item to the database
    await item.save();
    // Redirect to the inventory page after updating the item
    res.redirect('/inventory');
  } catch (err) {
    console.error('Error updating item:', err);
    res.status(500).send('Internal Server Error');
  }
});

app.listen(4001, () => {
    console.log("Server is listening at port 4001");
});
  
  