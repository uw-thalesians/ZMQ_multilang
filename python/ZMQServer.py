#!/usr/bin/env python2.7
import os
import zmq

context = zmq.Context()
socket = context.socket(zmq.REP)

socket.bind("tcp://*:5555")

pid = os.getpid()

while(True):
    msg= socket.recv()
    print pid, ":", msg
    socket.send("ACK")