Session 2 – ARQ Response Time & Error Analysis

Session 2 contains the second full measurement set for the virtual modem system.
This session focuses on response-time behavior, ARQ retransmissions, and error-rate estimation, using both clean and error-induced packet streams.

Included analyses:

* Echo response time under normal conditions

* Echo response time under ARQ with induced random bit errors

* Probability distribution of retransmissions for erroneous packets

* Supporting CSV datasets used to generate the plots

Contents
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
Plots Overview
G1 – Response Time (Normal Packets)

Echo response time for each received packet over a 4-minute window.
Key metrics extracted from dataset:

* Minimum RTT: 22 ms

* Maximum RTT: 358 ms

* Mean RTT: 38.5 ms

G2 – Response Time with ARQ (Error-Induced Channel)

Response time for packets successfully received under ARQ, using artificially introduced transmission errors.
The time distribution reflects retransmission overhead and jitter due to resends.

G3 – Retransmission Probability Distribution

Histogram showing the number of retransmissions required for erroneous packets.
Dataset summary:

* Total packets: 6230

* NACK: 938

* ACK: 5292

* Error probability:

𝑞
=
938
/
6230
≈
0.15
q=938/6230≈0.15

* Success probability:

𝑝
=
1
−
𝑞
=
0.85
p=1−q=0.85

* BER estimation using

𝐵
𝐸
𝑅
=
1
−
(
𝑎
𝑐
𝑘
𝑎
𝑐
𝑘
+
𝑛
𝑎
𝑐
𝑘
)
1
/
128
BER=1−(
ack+nack
ack
	​

)
1/128

Result: BER ≈ 0.0013

Purpose of Session 2

This session evaluates:

* System reliability under erroneous channel conditions

* ARQ effectiveness

* Impact of retransmissions on latency

* Statistical behavior of packet errors


All plots were produced directly from the included CSV datasets.
