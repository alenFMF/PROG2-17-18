def stej():
    with open("stej.txt") as f:
        for v in f:
            yield v.strip()
