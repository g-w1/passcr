# PASSCR

passcr is a tool to create passwords in bulk that conform to a certain format, with real words.

To setup this up, have a file named "dict" with 1 word per line in order of lowest size to highest size. A sample has been provided in this repo. Then run `make setup`.

This will compile everything and generate the temporary files for efficiency in `./shortened/`. You can remove the build artifacts with `make clean`.

All you have to do is run `./passcr pattern_string number`.

Here are some examples: 
`./passcr xxxnnbb 2`
```
rfz26)^
ave08)#
```

`./passcr xxxnbxxxx 2`
```
pci5^frey
tur1(spig
```

x means do a word
b means one of `!@#$%^&*()`
n means a number
