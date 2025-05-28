import Sidebar from "@/components/sidebar";
import {
  ResizableHandle,
  ResizablePanel,
  ResizablePanelGroup,
} from "@/components/ui/resizable";

export default function Example() {
  return (
    <main className="h-dvh w-full">
      <ResizablePanelGroup
        direction="horizontal"
        className="min-h-[200px] rounded-lg border md:min-w-[450px]"
      >
        <ResizablePanel defaultSize={24} maxSize={28} minSize={20}>
          <Sidebar></Sidebar>
        </ResizablePanel>
        <ResizableHandle />
        <ResizablePanel defaultSize={75} className="hidden md:flex">
          <div className="flex h-full items-center justify-center p-6">
            <span className="font-semibold">Content</span>
          </div>
        </ResizablePanel>
      </ResizablePanelGroup>
    </main>
  );
}
