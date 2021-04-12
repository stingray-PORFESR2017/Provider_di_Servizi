export class Departures {

  cancelled: boolean;
  delay: string;
  platform: string;
  time: string;
  trainNumber: string;
  trainHeader: {"id": string, "brandCustomer": string, "brandCategory"};
  messages: {
    "additionalData": {
      "content": string
    }
  };
  fullContent: string;
  stops: string[];

  constructor(cancelled?: boolean, delay?: string, platform?: string, time?: string, trainNumber?: string, trainHeader?: any, messages?: any ) {
    this.cancelled = cancelled;
    this.delay = delay;
    this.platform = platform;
    this.time = time;
    this.trainNumber = trainNumber;
    this.trainHeader = trainHeader;
    this.messages = messages;
  }
}
