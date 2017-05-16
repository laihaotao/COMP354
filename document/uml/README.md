# UML File

## File Format

Since we want to make the UML file available on the GitHub, here we are going to use text file instead 
of diagram. Before we use the text file, we need to set up some rules that make the file easily to read.

1. all the class name should be bold;
2. the enum class should with the `enum` before its name;
3. the interface should with the `interface` before its name;
4. use `implement` to indicate the interface this class will implement;
5. the inherit relationship should be using `extend` to point out its superclass;
6. classes with inherit relationship or follow some kind of logic can be input in one file (between 
each class there should be two blank line between them);
7. all the uml files should use `.uml.txt` extension;
8. all the member variables should with the `-` before it name and `:` before its type;
9. all the member methods should with the `+` before it name and `:` before its return type;
10. a blank line should be used to separate the variables and methods;
11. use `//` to represent comment in the file;
12. add the creator's name and all modifiers' name, it will make the file to be maintained easily;
13. only list its variables and methods (include the methods you implement or override, exclude the 
variables and methods extend from the superclass);

## Example

Assume we have a cat class.

```
// the file name will be "Cat.uml.txt"
// creator: Haotao(Eric) Lai
// modifiers: Eric1, Eric2, ... (all people who modify this file should put their name here)

class Cat extend Animal implement Eatable
- name: String
- age: int

+ jump(): void
+ eat(): void
```

## Template

Here is a template for create a new UML file:

```
// creator: 
// modifiers: 

class {class_name} extend {supper_class_name} implement {interface_name}
-
-

+
+
```