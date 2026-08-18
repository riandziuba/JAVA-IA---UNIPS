import CounterProvider from "@/context/CounterContext";

export default function Layout({ children }: LayoutProps<"/class-2/level-2">) {
    return (
        <CounterProvider>{children}</CounterProvider>
    );
}
