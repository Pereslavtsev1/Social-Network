import { Avatar, AvatarFallback, AvatarImage } from "@/components/ui/avatar";
import { Chat } from "../types";
import { Check, CheckCheck } from "lucide-react";
import React from "react";
import { Button } from "../ui/button";

const SidebarButton = ({ chat }: { chat: Chat }) => {
  return (
    <Button
      variant={"ghost"}
      size={"lg"}
      className="flex w-full items-center justify-between rounded-lg px-3 py-7"
    >
      <div className="flex gap-2">
        <Avatar className="size-12">
          <AvatarImage src={chat.avatar} alt={`${chat.name}'s avatar`} />
          <AvatarFallback>{chat.name.charAt(0)}</AvatarFallback>
        </Avatar>

        <div className="flex items-start flex-col gap-1">
          <h1 className="font-bold">{chat.name}</h1>
          <p className="text-sm text-muted-foreground">{chat.lastMessage}</p>
        </div>
      </div>

      <div className="flex items-center gap-2">
        <LastMessageStatus chat={chat} className="size-4" />
        <p className="text-sm text-muted-foreground">{chat.timestamp}</p>
      </div>
    </Button>
  );
};

const LastMessageStatus = ({
  chat,
  className = "",
}: {
  chat: Chat;
  className?: string;
}) => {
  const getStatusIcon = () => {
    switch (chat.messageStatus) {
      case "sent":
        return (
          <Check
            className={`${className} text-blue-500`}
            data-tooltip="Message sent"
          />
        );
      case "received":
        return (
          <Check
            className={`${className} text-green-500`}
            data-tooltip="Message received"
          />
        );
      case "delivered":
        return (
          <Check
            className={`${className} text-muted-foreground`}
            data-tooltip="Message delivered"
          />
        );
      case "read":
        return (
          <CheckCheck
            className={`${className} text-foreground`}
            data-tooltip="Message read"
          />
        );
      default:
        return null;
    }
  };

  return <div className="relative">{getStatusIcon()}</div>;
};

export default SidebarButton;
