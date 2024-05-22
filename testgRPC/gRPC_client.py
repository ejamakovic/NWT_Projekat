import grpc
from system_events_pb2 import SystemEventsRequest
from system_events_pb2_grpc import SystemEventsStub


def run():
    # Create a gRPC channel
    channel = grpc.insecure_channel("localhost:6565")

    # Create a stub (client)
    stub = SystemEventsStub(channel)

    # Create a valid request message
    request = SystemEventsRequest(
        date="2022-01-01",
        microservice="service1",
        user="user1",
        action="action1",
        resource="resource1",
        response="response1",
    )

    # Make the call
    response = stub.GetSystemEvents(request)

    # Et voila
    print(response.status)


if __name__ == "__main__":
    run()
