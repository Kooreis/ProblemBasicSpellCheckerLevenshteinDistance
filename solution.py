def correction(word): 
    return max(candidates(word), key=P)