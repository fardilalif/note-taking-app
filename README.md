# NoteApp

Simple note taking application written in Java.


What I use in this application :
 1. MVVM architecture to build this app.
 2. SQLiteDatabase to store and receive all data. I use Room Persistence Library to create SQLiteDatabase.
 3. LiveData to receive data and show it to UI.
 4. ListAdapter in DiffUtil class to add animation in RecyclerView if there is any changes.
 5. ItemTouchHelper to add swipe functionality in order to delete note.
 6. OnItemClickListener is used to add update functionality.
