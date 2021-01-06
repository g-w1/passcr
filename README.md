# PASSCR

passcr is a tool to create passwords in bulk that conform to a certain format, with real words.

To setup this up, have `javac` and `gnumake` installed and then run `make`.

You can remove the build artifacts with `make clean`.

All you have to do is run `./passcr pattern_string number dictfile`. A sample dictfile has been provided for you called "dict". A dictfile must have one word per line.

Here are some examples: 
`./passcr xxxnnbb 2 dict`
```
rfz26)^
ave08)#
```

`./passcr xxxnbxxxx 2 dict`
```
pci5^frey
tur1(spig
```

x means do a word of length how many x's in a row

b means one of `!@#$%^&*()`

n means a number
