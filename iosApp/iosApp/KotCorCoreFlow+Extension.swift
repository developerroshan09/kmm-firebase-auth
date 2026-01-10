//import ComposeApp
//import Foundation
//
//extension Kotlinx_coroutines_coreFlow {
//
//    func stream<T>() -> AsyncStream<T> {
//        AsyncStream { continuation in
//
//            // Completion handler
//            let completionHandler: (Error?) -> Void = { _ in
//                continuation.finish()
//            }
//
//            // Collector closure
//            let collectLambda: (Any?) -> Void = { value in
//                if let v = value as? T {
//                    continuation.yield(v)
//                }
//            }
//
//            // Call Kotlin collect
//            let job = self.collect(collector: collectLambda, completionHandler: completionHandler)
//
//            continuation.onTermination = { _ in
//                job.cancel(cause: nil as KotlinThrowable?) // Explicit type for nil
//            }
//        }
//    }
//}
//
////extension Kotlinx_coroutines_coreFlow {
////
////    func stream<T>() -> AsyncStream<T> {
////        AsyncStream { continuation in
////
////            // Completion handler
////            let completionHandler: (Error?) -> Void = { _ in
////                continuation.finish()
////            }
////
////            // Collector closure
////            let collectLambda: (Any?) -> Void = { value in
////                if let v = value as? T {
////                    continuation.yield(v)
////                }
////            }
////
////            // Call Kotlin collect
////            let job = self.collect(collector: collectLambda, completionHandler: completionHandler)
////
////            continuation.onTermination = { _ in
////                job.cancel(cause: nil as KotlinThrowable?) // Explicit type for nil
////            }
////        }
////    }
////}
////
////
////
////class AsyncFlowCollector<T>: Kotlinx_coroutines_coreFlowCollector {
////    let continuation: AsyncStream<T>.Continuation
////
////    init(continuation: AsyncStream<T>.Continuation) {
////        self.continuation = continuation
////        super.init()
////    }
////
////    override func emit(_ value: Any?, completionHandler: @escaping (Error?) -> Void) {
////        if let v = value as? T {
////            continuation.yield(v)
////        }
////        completionHandler(nil)
////    }
////}
////
