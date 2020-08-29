# JEV3
JEV3 is a collection of java packages designed to aid in interaction with EV3 related files and file formats.

## rgf
The `.rgf` file format is the image format used by EV3. JEV3 provides the `RgfReader` class that can be used to read `.rgf` files.
```java
BufferedImage bi = RgfReader.read(new File("/image.rgf"));
```
The `read` method accepts either a `File` object, a `Path` object or a `byte[]`.
