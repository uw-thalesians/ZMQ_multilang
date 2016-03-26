#include <string>
#include <iostream>
#include "ZMQ.h"
#include <stdlib.h>
#include <stdio.h>
#include <conio.h>

int main(int argc, char*argv[])
{
	int major, minor, patch;
	zmq_version(&major, &minor, &patch);
	std::cout << "ZMQ v" << major << "." << minor << "." << patch;

	void *context = zmq_ctx_new();
	void *socket = zmq_socket(context, ZMQ_REQ);

	//zmq_bind(socket, "tcp://*:5555");

	zmq_connect(socket, "tcp://localhost:5555");

	char msg[33];
	::memset(msg, 0, 33);

	while (!_kbhit())
	{
		zmq_send(socket, "message from client", 20, 0);

		zmq_recv(socket, msg, 33, 0);

		std::cout << msg;
	}

	zmq_close(socket);

	zmq_ctx_term(context);


}