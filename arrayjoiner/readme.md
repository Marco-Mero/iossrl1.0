# Progetto dipendente da ArrayUtils

## Requisiti Fondamentali Generici

0. Le consegne, specialmente i requesiti richiesti, vanno rispettati per tutto il progetto.
1. Ogni esercizio dovrà essere un progetto indipendente.
2. La leggibilità del codice, al netto del clean code, è data dalla suddivisione delle funzionalità nelle classi adeguate.
3. L'obiettivo generale degli esercizi è anche valutare la capacità di gestire in maniera corretta la suddivisione delle varie classi nei package adeguati.
4. Richiediamo un utilizzo delle risorse ottimizzate riducendo i cicli di clock usati e ridurre al minimo le allocazioni in memoria, evitare quindi di aggiungere overhead sia come implementazione di codice sia come importazione di librerie o framework esterni salvo sia strettamente necessario.

---

### **1) Unione Array**

**Obiettivo:** Attestare la capacità logica dello sviluppatore, la sua capacità di ottimizzazione e di pulizia del codice.

**Requisiti:** In questo esercizio non è possibile utilizzare nessuna classe, proprietaria di Java, che richieda di essere istanziata (es. no ArrayList, ecc).

**Consegna:**
Scrivere un metodo che in input riceva 2 array e restituisca l'unione dei 2 array. Il metodo deve essere in grado di gestire array in input di qualunque dimensione.
I valori all' interno dell'array unito non devono essere ripetuti.

```
Es:
A = [3, 2, 4, 7, 4, 5]
B = [3, 1, 9]
Risultato:
[3, 2, 4, 7, 5, 1, 9]
```

---
