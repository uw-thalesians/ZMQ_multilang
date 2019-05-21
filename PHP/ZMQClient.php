<?php

//include 'zmsg.php';

$context = new ZMQContext();
$socket = new ZMQSocket($context, ZMQ::SOCKET_REQ);

$socket->connect("tcp://localhost:5555");

$pid = posix_getpid();

while(true) {
    $socket->send("message from client $pid");
    $msg = $socket->recv();
    echo "$pid : $msg";
}

?>