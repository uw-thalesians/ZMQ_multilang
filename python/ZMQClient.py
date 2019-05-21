#!/usr/bin/env python2.7

import zmq
import os

context = zmq.Context()
socket = context.socket(zmq.REQ)

socket.connect("tcp://localhost:5555")

pid = os.getpid()

while(True):
    socket.send(str("message from client ") + str(pid))
    msg = socket.recv()
    print pid, ":", msg