# Airfoil from .dat to CAD

A simple tool to convert the non-dimensional .dat file into a new curve, that can be rotated and placed in a plan at a specific distance from the wing or propeller root.

### How It Works

The program recives as an input:

* One dat file;
* Chord of the section;
* Angle of rotation;
* The distance between section and root.

Now thats the condition has been set, the .dat file will be loaded multiplied by the chord to give it the proper size, and rotated with the follwing operation:

```
Airfoil X B = Ro
```
|----------:|
|cos    -sin|
|-cos    sin|
