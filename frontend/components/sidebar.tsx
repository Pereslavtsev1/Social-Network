import { Avatar, AvatarFallback, AvatarImage } from "@/components/ui/avatar";

const Sidebar = () => {
  const chats = [
    {
      id: 1,
      name: "Alice Johnson",
      avatar: "/placeholder.svg?height=40&width=40",
      lastMessage: "Hey! How are you doing?",
      timestamp: "2:30 PM",
      unread: 3,
      online: true,
      messageStatus: "delivered",
    },
    {
      id: 2,
      name: "Work Team",
      avatar: "/placeholder.svg?height=40&width=40",
      lastMessage: "Meeting at 3 PM today",
      timestamp: "1:45 PM",
      unread: 0,
      online: false,
      messageStatus: "read",
      isGroup: true,
    },
    {
      id: 3,
      name: "Bob Smith",
      avatar: "/placeholder.svg?height=40&width=40",
      lastMessage: "Thanks for the help!",
      timestamp: "12:15 PM",
      unread: 0,
      online: false,
      messageStatus: "read",
    },
    {
      id: 4,
      name: "Family Group",
      avatar: "/placeholder.svg?height=40&width=40",
      lastMessage: "Mom: Don't forget dinner tonight",
      timestamp: "11:30 AM",
      unread: 1,
      online: false,
      messageStatus: "delivered",
      isGroup: true,
    },
    {
      id: 5,
      name: "Sarah Wilson",
      avatar: "/placeholder.svg?height=40&width=40",
      lastMessage: "Can you send me the files?",
      timestamp: "Yesterday",
      unread: 0,
      online: true,
      messageStatus: "sent",
    },
    {
      id: 6,
      name: "Project Alpha",
      avatar: "/placeholder.svg?height=40&width=40",
      lastMessage: "New updates available",
      timestamp: "Yesterday",
      unread: 5,
      online: false,
      messageStatus: "read",
      isGroup: true,
    },
    {
      id: 7,
      name: "Mike Chen",
      avatar: "/placeholder.svg?height=40&width=40",
      lastMessage: "See you tomorrow!",
      timestamp: "Monday",
      unread: 0,
      online: false,
      messageStatus: "read",
    },
  ];
  return (
    <aside>
      <header>Header</header>
      <main>
        <div className="flex w-full flex-col gap-1">
          {chats.map((chat) => (
            <div key={chat.id}>
              <Avatar>
                <AvatarImage src="https://github.com/shadcn.png" />
                <AvatarFallback>CN</AvatarFallback>
              </Avatar>
            </div>
          ))}
        </div>
      </main>
    </aside>
  );
};

export default Sidebar;
