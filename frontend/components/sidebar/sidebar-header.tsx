import { AlignJustify, Search } from "lucide-react";
import { Input } from "@/components/ui/input";

const SidebarHeader = () => {
  return (
    <header className="flex items-center gap-4 py-2">
      <AlignJustify className="ml-4 h-5 w-5 cursor-pointer" />
      <div className="relative flex-1">
        <Input
          variant="custom"
          type="text"
          placeholder="Search..."
          className="px-10"
        />
        <Search className="absolute top-1/2 left-3 h-4 w-4 -translate-y-1/2 text-muted-foreground" />
      </div>
    </header>
  );
};

export default SidebarHeader;
