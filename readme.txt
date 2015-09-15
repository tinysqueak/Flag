The purpose of this project was to create U.S. Flag applet that scales.

This fulfills the specifications as a scalable U.S. Flag in the correct proportions was drawn and displayed on a frame. An ambiguity in the specifications involved the issue of the exact colors of the flag. The specifications did not include mention of the exact color to be used, so the exact colors were taken from a third party website that provided information on them.

Several shortcomings of the project as is include some minor issues with the stars disappearing from the display if the window is resized to very small proportions. In addition, the scalability of the flag is not extremely smooth. These issues could be resolved by breaking apart the various flag components into smaller portions, such that an accurate rendering of the stars and and a more accurate scalability can be achieved.

The overall structure of the code invovles first defining the necessary ratios needed for calculations and the various colors of the flag. The program then determines the necessary dimensions of the flag from the current windows size and passes these values to various draw methods. These methods, in order of their invocations, draw the background, the stripes, the union, and finally the stars. Further information regarding the specifics of each part of the code are available in the comments in the code itself.

Some challenges in that encountered in the process of making the code include properly drawing the stars and properly scaling the flag.
