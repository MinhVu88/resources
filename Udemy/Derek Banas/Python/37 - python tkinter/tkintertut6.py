from tkinter import *
import tkinter.filedialog


class TextEditor:

    # Quits the TkInter app when called
    @staticmethod
    def quit_app(event=None):
        root.quit()

    # ----- NEXT TUTORIAL -----

    def remake_file(self, text_area_list):
        for i in text_area_list:
            print("Key", i[0])
            print("Value", i[1])
            print("Index", i[2])

    # ----- END NEXT TUTORIAL -----

    def open_file(self, event=None):
        # Open dialog and get chosen file
        txt_file = tkinter.filedialog.askopenfilename(parent=root,
                                                      initialdir='/')
        # If the file exists
        if txt_file:

            self.text_area.delete(1.0, END)

            # Open file and put text in the text widget
            with open(txt_file) as _file:
                self.text_area.insert(1.0, _file.read())

                # Update the text widget
                root.update_idletasks()

    def save_file(self, event=None):

        # Opens the save as dialog box
        file = tkinter.filedialog.asksaveasfile(mode='w')
        if file is not None:
            # Get text in the text widget and delete the last newline
            data = self.text_area.get('1.0', END + '-1c')

            # Write the text and close
            file.write(data)

            # ----- NEXT TUTORIAL -----

            # print(str(self.text_area.dump('1.0', END)))
            # self.remake_file(self.text_area.dump('1.0', END))

            # ----- END NEXT TUTORIAL -----

            file.close()

    def make_bold(self):
        self.text_area.tag_add("bt", "sel.first", "sel.last")

    def __init__(self, root):

        self.text_to_write = ""

        # Define title for the app
        root.title("Text Editor")

        # Defines the width and height of the window
        root.geometry("600x550")

        frame = Frame(root, width=600, height=550)

        # Create the scrollbar
        scrollbar = Scrollbar(frame)

        # yscrollcommand connects the scroll bar to the text
        # area
        self.text_area = Text(frame, width=600, height=550,
                        yscrollcommand=scrollbar.set,
                        padx=10, pady=10, font=("Georgia", "14"))

        # Call yview when the scrollbar is moved
        scrollbar.config(command=self.text_area.yview)

        # Put scroll bar on the right and fill in the Y direction
        scrollbar.pack(side="right", fill="y")

        # Pack on the left and fill available space
        self.text_area.pack(side="left", fill="both", expand=True)
        frame.pack()

        # ----- FILE MENU CREATION -----

        # Create a pull down menu that can't be removed
        file_menu = Menu(the_menu, tearoff=0)

        # Add items to the menu that show when clicked
        # compound allows you to add an image
        file_menu.add_command(label="Open", command=self.open_file)
        file_menu.add_command(label="Save", command=self.save_file)

        # Add a horizontal bar to group similar commands
        file_menu.add_separator()

        # Call for the function to execute when clicked
        file_menu.add_command(label="Quit", command=self.quit_app)

        # Add the pull down menu to the menu bar
        the_menu.add_cascade(label="File", menu=file_menu)

        # ----- EDIT MENU CREATION -----

        edit_menu = Menu(the_menu, tearoff=0)
        edit_menu.add_command(label="Bold", command=self.make_bold)
        the_menu.add_cascade(label="Edit", menu=edit_menu)

        self.text_area.tag_config("bt", font=("Georgia", "14", "bold"))


        # Display the menu bar
        root.config(menu=the_menu)


root = Tk()

# Create the menu object
the_menu = Menu(root)

text_editor = TextEditor(root)

root.mainloop()

