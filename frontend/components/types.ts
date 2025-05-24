export type Chat = {
  id: number;
  name: string;
  avatar: string;
  lastMessage: string;
  timestamp: string;
  unread: number;
  online: boolean;
  messageStatus: "sent" | "received" | "delivered" | "read";
  isGroup?: boolean;
};
