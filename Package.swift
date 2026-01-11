// swift-tools-version:5.9
import PackageDescription

let package = Package(
    name: "KmmFirebaseAuth",
    platforms: [
        .iOS(.v13)
    ],
    products: [
        .library(
            name: "KmmFirebaseAuth",
            targets: ["KmmFirebaseAuth"]
        )
    ],
    targets: [
        .binaryTarget(
            name: "KmmFirebaseAuth",
            url: "https://github.com/developerroshan09/kmm-firebase-auth/releases/download/1.0.0/KmmFirebaseAuth.xcframework.zip",
            checksum: "fd4c7a55304e8ea2a5de16cfddedc2fcb6c2bfae915edf27e62bdf56b460e2c5"
        )
    ]
)