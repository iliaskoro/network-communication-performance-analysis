<h1>Session 2 – ARQ Response Time & Error Analysis</h1>

Session 2 contains the second full measurement set for the virtual modem system.
This session focuses on response-time behavior, ARQ retransmissions, and error-rate estimation, using both clean and error-induced packet streams.

Included analyses:

* Echo response time under normal conditions

* Echo response time under ARQ with induced random bit errors

* Probability distribution of retransmissions for erroneous packets

* Supporting CSV datasets used to generate the plots

<h2>Contents</h2>

```
session2/
├── data/
│   ├── virtualModempackets_with_errors.csv
│   ├── virtualModempackets_without_errors.csv
│
├── plots/
│   ├── g1.png
│   ├── g2.png
│   ├── g3.png
│
├── audio/
│
└── README.md
```

<h2>Plots Overview</h2>
<h3>G1 – Response Time (Normal Packets)</h3>

Echo response time for each received packet over a 4-minute window.
Key metrics extracted from dataset:

* Minimum RTT: 22 ms

* Maximum RTT: 358 ms

* Mean RTT: 38.5 ms

<h3>G2 – Response Time with ARQ (Error-Induced Channel)</h3>

Response time for packets successfully received under ARQ, using artificially introduced transmission errors.
The time distribution reflects retransmission overhead and jitter due to resends.

<h3>G3 – Retransmission Probability Distribution</h3>

Histogram showing the number of retransmissions required for erroneous packets.
Dataset summary:

* Total packets: 6230

* NACK: 938

* ACK: 5292

* Error probability:

$$ q = \frac{938}{6230} \approx 0.15 $$

* Success probability:

$$ p = 1 - q = 0.85 $$

* BER estimation using

$$
BER = 1 - \left( \frac{ack}{ack + nack} \right)^{1/128}
$$

Result: BER ≈ 0.0013

<h2>Purpose of Session 2</h2>

This session evaluates:

* System reliability under erroneous channel conditions

* ARQ effectiveness

* Impact of retransmissions on latency

* Statistical behavior of packet errors


All plots were produced directly from the included CSV datasets.

