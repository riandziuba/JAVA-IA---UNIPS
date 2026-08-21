import CounterProvider from "@/context/class-2/CounterContext";

export default function Layout({ children }: LayoutProps<"/class-2/level-2">) {
    return (
        <CounterProvider>{children}</CounterProvider>
    );
}
