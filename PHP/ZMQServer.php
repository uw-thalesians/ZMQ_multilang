<?php

$context = new ZMQContext();
$socket = new ZMQSocket($context, ZMQ::SOCKET_REP);

$socket->bind("tcp://*:5555");

$pid = posix_getpid();

while(true) {
    $msg = $socket->recv();
    echo "$pid : $msg";
    $socket->send("ACK");
}

?>