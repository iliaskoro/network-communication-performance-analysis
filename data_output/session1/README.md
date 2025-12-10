<h1>Session 1 – System Response Analysis & Packet Retransmission</h1>

Session 1 includes the complete analysis of system response time and retransmission behavior using virtual modem packet logs.
The data were processed to produce three plots:

- System response time (G1)

- ARQ-assisted response time under induced errors (G2)

- Probability distribution of packet retransmissions (G3)

<h2>Contents</h2>

```
session1/
├── data/
│   ├── virtualModempackets_with_errors.csv
│   ├── virtualModempackets_without_errors.csv
│
├── plots/
│   ├── g1.png
│   ├── g2.png
│   ├── g3.png
│
└── README.md
```
<h3>G1 – System Response Time</h3>

Response time for each packet over a continuous measurement window.
The plot illustrates system delay variability and peak latency events.

<h3>G2 – ARQ Response Time (With Errors)</h3>

Response time for packets successfully received with the assistance of ARQ under artificially induced error conditions.

<h3>G3 – Retransmission Probability Distribution</h3>

Distribution of the number of retransmissions required for erroneous packets.
This plot highlights the error characteristics of the communication channel and the behavior of the ARQ mechanism.

<h2>Data Files</h2>

The raw CSV logs stored in data/ include:

- Packets received without errors

- Packets received with errors and retransmissions

These files were used to calculate delays, identify ACK/NACK sequences, and generate the three plots.


