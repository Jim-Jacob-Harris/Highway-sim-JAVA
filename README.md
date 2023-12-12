This was just a school project. It's supposed to be a highway simulation but I made some changes to it.

The logic behind it is:

There are X+1 Entries where cars can enter/exit (On the first and last entry, cars can only enter and exit respectively) and every Entery has X segments:

Bad Visual example: | -> Enrty , = -> Segment

| = = = = = | = = = = = | = = = = = | = = = = = |

Every segment has M cars max capacity.

Every Entry has K booths with workers and K * 2 e-booths (or the other way around, I don't remember), meaning that only K+K*2 cars can enter at a time.

Cars can enter a segment throught it's booths, pass to another segment if they can, and exit the highway randomly from every entry.

K can increase by one if more cars need to enter than the cars that can actually enter (M max capacity). If less enter then k-1.
Also mention if there is a delay on a booth.

I think that's ,roughly, everything.

Have a nice day !
