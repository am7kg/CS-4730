# CS-4730
Lab 6 Engine : CS 4730

Lab 6 engine is located inside the lab1 folder

View the ReadMe in raw from for clarity

=====================================================================================================

Notes from class: 25 / 7 / 2016

Blobs Only Job

Blob is trying to be productive
Distractions pop up
Point and click
2 different endings
humorous tone
4 levels
•	Main thing
•	3 distraction levels (bar, restaurant, movies, etc.)
Chain of events
Items to show progress
Events for the prototype

Prototype:
Click on things
Pop up windows
Blob can move
Basic brushing the house

Alpha:
First onion
The first few things you can do in the game
And rest of game is planned out


=====================================================================================================

Prototype notes: 25 / 7 / 2016
Author: Ali C.

Clicker Mechanic
  Reuse MouseListener class from Lab 1
  Clicking on certain things (Sprites) dispatch events
    Our event listener (QuestManager) will prompt the user (accept quest, come back later, etc.)
    Quest activates : should the player move Blob to his destination, or should the game teleport the player?
  // pseudo code
  void mouseReleased(MouseEvent e)
    clickedItem(e.getX(), e.getY()); //clickedItem is a method that checks if the mouse clicked an item, if so throw an event
  void clickedItem(int x, int y)
    /*Where ItemList is the list of items currently drawn on screen
    * In our update() method, we can add() and remove() items to the ItemList 
    * ArrayList<ItemSprite> ItemList = new ArrayList<ItemSprite>
    */ ItemSprite is a class that extends Sprite and is used to instantiate the items ( Phone, Paint, Keys, Wallet, Beer, etc. )
    for ( Object i : ItemList ) 
      if ( i.getID() == ClickEvent.PHONE_ITEM ) // For example
        Blob.dispatchEvent(new ClickEvent(ClickEvent.PHONE_ITEM, Blob)); 
        // Our Quest Manager will be listening to this and handle it accordingly
  void handleEvent(ClickEvent event)
    if ( event.eventType == ClickEvent.PHONE_ITEM )
      prompt window ("accept quest?");
      user input ("yes", "no");
      if (input == "yes" )
        event.getSource().dispatchEvent( new GameEvent(GameEvent.PHONE_QUEST, event.getSource() ); // Begin quest!
      if (input == "no" )
        break;
    if ( event.eventType == GameEvent.PHONE_QUEST )
      ((Blob) event.getSource()).addInventory(new PocketItem("Phone"));
      /* Pocket Item extends Sprite, and represents the item in pocket form when Blob has picked up the item
      *  After this the phone on display disappears (since Blob has it)
      * This general idea of chaining events as the Game's main mechanic
      * Repeat the process for each item/quest/event/text box/function etc. etc.
      */ 
  
Movement
  Arrow Keys
  What does moving Blob let the player do? Should the only user input come from clicking the mouse?
  //pseudo code
    Blob.update(pressedKeys);
    if (pressedKeys.contains("up");
      Block.getPosition().translate(0,-5);
    if (pressedKeys.contains("down");
      Block.getPosition().translate(0,5);
    if (pressedKeys.contains("right");
      Block.getPosition().translate(5,0);
    if (pressedKeys.contains("left");
      Block.getPosition().translate(-5,0);
  
Game over : How does the game end?
  Item limit;
  if ( Blob paints house first )
    item limit lifted; // Player can now do anything with Blob
  if ( Blob paints house )
    new item( game over ); // Player can now use this item to end the game whenever
  if ( Blob.hasItemLimit == true )
    if ( Blob.getNumberOfItems >= item limit )
      game over;

Tweening
  For finishing touches in the final product, tweening might add humour to the visuals. Thoughts?


