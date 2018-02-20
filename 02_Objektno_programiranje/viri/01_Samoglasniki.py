def samoglasniki(niz):
    for z in niz:
        if z.lower() in "aeiou":
            yield z

niz = "abeCE DA 12tRI"
