def stevilaVDatoteki(ime):
    with open(ime) as f:
        for v in f:
            st = ""
            for z in v:
                if z.isdigit():
                    st += z
                else:
                    if len(st) > 0:
                        tmp = st
                        st = ""
                        yield tmp
        if len(st) > 0:
            yield st
