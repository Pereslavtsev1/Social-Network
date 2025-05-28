import SidebarButton from "./sidebar/sidebar-button";
import SidebarHeader from "./sidebar/sidebar-header";
import { Chat } from "./types";

const Sidebar = () => {
  const chats: Chat[] = [
    {
      id: 1,
      name: "Alice Johnson",
      avatar: "/placeholder.svg?height=40&width=40",
      lastMessage:
        "Hey! How are you doing? Hey! How are you doing? Hey! How are you doing?",
      timestamp: "2:30 PM",
      unread: 3,
      online: true,
      messageStatus: "read",
    },
  ];
  return (
    <aside className="px-2">
      <SidebarHeader></SidebarHeader>
      <main className="pt-2">
        <div className="flex w-full flex-col gap-1">
          {chats.map((chat) => (
            <SidebarButton chat={chat} key={chat.id} />
          ))}
        </div>
      </main>
    </aside>
  );
};

export default Sidebar;
